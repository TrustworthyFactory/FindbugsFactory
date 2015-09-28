package com.thalesgroup.optet.analysis.findbugs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.viewers.ISelection;
import org.osgi.framework.Bundle;

import com.thalesgroup.optet.common.data.OptetDataModel;
import com.thalesgroup.optet.common.data.Severity;
import com.thalesgroup.optet.twmanagement.Evidence;
import com.thalesgroup.optet.twmanagement.Metric;
import com.thalesgroup.optet.twmanagement.impl.OrchestrationImpl;

import de.tobject.findbugs.FindbugsPlugin;
import de.tobject.findbugs.util.Util;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Detector;
import edu.umd.cs.findbugs.DetectorFactoryCollection;
import edu.umd.cs.findbugs.FindBugs;
import edu.umd.cs.findbugs.FindBugs2;
import edu.umd.cs.findbugs.Project;
import edu.umd.cs.findbugs.XMLBugReporter;
import edu.umd.cs.findbugs.config.UserPreferences;


public class FindbugsUtil extends AuditToolUtil{

	// convert the absolut path and the short path
	private Map <String, String> pathConverter = null ;

	// FindBugs user preference
	private UserPreferences userPreferences = null; 

	// findbugs category from the preference page
	private List<String> userPrefCatAbbrev = null;



	/**
	 * constructor
	 * @param iProject
	 * @param fileList
	 */
	public FindbugsUtil(IProject iProject, List<IFile> fileList) {
		super(iProject, fileList);
		pathConverter = new HashMap<>();

	}
	
	public static File resolveFile(String path,Bundle bundle) throws IOException {
		  File file=null;
		  if (bundle != null) {
		    URL url=FileLocator.find(bundle,new Path(path),Collections.emptyMap());
		    if (url != null) {
		      URL fileUrl=FileLocator.toFileURL(url);
		      try {
		        file=new File(fileUrl.toURI());
		      }
		 catch (      URISyntaxException e) {
		        e.printStackTrace();
		      }
		    }
		  }
		  return file;
		}

	private void runSpecificAudit(String metric, String projectType){

		try {
			// create the findbugs engine
			final FindBugs2 engine = new FindBugs2();

			// configure the engine
			Project project = getFindbugsProject(getIFileList());
			engine.setProject(project);
			engine.setDetectorFactoryCollection(DetectorFactoryCollection.instance());

			XMLBugReporter xmlBugReporter = new XMLBugReporter(project);
			xmlBugReporter.setPriorityThreshold(Detector.LOW_PRIORITY);
			xmlBugReporter.setAddMessages(true);
			// xmlBugReporter.setErrorVerbosity(BugReporter.SILENT);

			xmlBugReporter.setOutputStream(new PrintStream(new FileOutputStream("NUL:")));
			//xmlBugReporter.setOutputStream(System.out);
			engine.setBugReporter(xmlBugReporter);
			engine.setAnalysisFeatureSettings(FindBugs.DEFAULT_EFFORT);
			userPreferences=  FindbugsPlugin.getUserPreferences(iProject);

			UserPreferences pref = UserPreferences.createDefaultUserPreferences();
			Map<String, Boolean> map = pref.getIncludeFilterFiles();


			if (projectType.equals("java")){
				if (metric.equals("SecurityMetric")){
					// nothing
//					OptetDataModel.getInstance().configureRulesMetric(metric, 0);

				} else if (metric.equals("SoftwareComplexityMetric")){
					// nothing
//					OptetDataModel.getInstance().configureRulesMetric(metric, 0);

				}else if (metric.equals("interceptet errors")){
					Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
					URL fileURL = bundle.getEntry("resources/FindBugsException.xml");
					String file = null;
					try {
						file = new File(FileLocator.resolve(fileURL).getFile()).toString();
						System.out.println("FindBugsReliability path " + file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					if (file==null)
						file= FindbugsUtil.resolveFile("resources/FindBugsException.xml", bundle).toString();
					
					map.put(file, true);
					OptetDataModel.getInstance().configureRulesMetric(metric, 9);

				}else if (metric.equals("MaintainabilityMetric")){
					// nothing
//					OptetDataModel.getInstance().configureRulesMetric(metric, 0);

				}
			} else if (projectType.equals("android")){
				if (metric.equals("SecurityMetric")){
					// nothing
//					OptetDataModel.getInstance().configureRulesMetric(metric, 0);

				} else if (metric.equals("SoftwareComplexityMetric")){
					// nothing
//					OptetDataModel.getInstance().configureRulesMetric(metric, 0);

				}else if (metric.equals("interceptet errors")){
					Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
					URL fileURL = bundle.getEntry("resources/FindBugsExceptionAndroid.xml");
					String file = null;
					try {
						file = new File(FileLocator.resolve(fileURL).getFile()).getAbsolutePath();
						System.out.println("FindBugsReliability path " + file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					if (file==null)
						file= FindbugsUtil.resolveFile("resources/FindBugsExceptionAndroid.xml", bundle).getAbsolutePath();

					map.put(file, true);
					OptetDataModel.getInstance().configureRulesMetric(metric, 9);

				}else if (metric.equals("MaintainabilityMetric")){
					// nothing
//					OptetDataModel.getInstance().configureRulesMetric(metric, 0);

				}

			}

			for(Entry<String, Boolean> entry : map.entrySet()) {
				String cle = entry.getKey();
				Boolean valeur = entry.getValue();
				System.out.println(cle + "   " + valeur);
				// traitements
			}

			if (map.size()!=0){
				pref.setIncludeFilterFiles(map);
				engine.setUserPreferences(pref);	
				engine.finishSettings();
				// execute findbugs
				engine.execute();

				//Transfer the findbugs report to the optet data model
				findbugsToOptet(engine.getBugReporter(),metric);
			}
		} catch (Exception e) {
			PluginHelper.getInstance().logException(e, e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see eu.optet.audittools.AuditToolUtil#runAudit()
	 */
	public void runAudit(IProject project, List<Evidence> evidences, String projectType){
		for (Iterator iterator = evidences.iterator(); iterator.hasNext();) {
			Evidence evidence = (Evidence) iterator.next();
			Map<String, Metric> metrics = evidence.getMetrics();
			for(Entry<String, Metric> entry : metrics.entrySet()){
				String key = entry.getKey();
				runSpecificAudit( key, projectType);
			}
		}


	}

	public edu.umd.cs.findbugs.Project getFindbugsProject(List<IFile> files) throws IOException, CoreException {
		edu.umd.cs.findbugs.Project findbugsProject = new edu.umd.cs.findbugs.Project();

		Set<IPath> outLocations = createOutputLocations();

		Iterator<IFile> iter = files.iterator();
		Map<File, String> outputFiles = new HashMap<File, String>();
		while (iter.hasNext()) {
			// get the resource
			IFile res = iter.next();
			if (res == null) {
				continue;
			}

			IPath location = res.getLocation();
			if (Util.isClassFile(res) && containsIn(outLocations, location)) {
				// add this file to the work list:
				String fileName = location.toOSString();

				res.refreshLocal(IResource.DEPTH_INFINITE, null);
				findbugsProject.addFile(fileName);
			}
			else if (Util.isJavaFile(res)) {
				// this is a .java file, so get the corresponding .class file(s)
				// get the compilation unit for this file
				ICompilationUnit cu = JavaCore.createCompilationUnitFrom(res);
				if (cu == null) {
					//					if (DEBUG) {
					//						FindbugsPlugin.getDefault().logError("NULL Compilation Unit for "+res.getName());
					//					}
					continue; // ignore and continue
				}
				// find the output location for this CompilationUnit
				IPackageFragmentRoot pkgRoot = (IPackageFragmentRoot) cu.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT);
				if (pkgRoot == null) {
					//					if (DEBUG) {
					//						FindbugsPlugin.getDefault().logError("NULL Package Root for: "+res.getName());
					//					}
					continue; // ignore and continue
				}
				IClasspathEntry cpe;
				// fix for Eclipse 3.3: if RawClasspathEntry is missing, 3.2 just
				// returns null but 3.3 throws JavaModelException
				try {
					cpe = pkgRoot.getRawClasspathEntry();
				} catch (JavaModelException e) {
					//					if (DEBUG) {
					//						FindbugsPlugin.getDefault().logError("EXCEPTION for: "
					//								+ res.getName() + ".getRawClasspathEntry()");
					//					}
					continue; // ignore and continue
				}
				if (cpe == null) {
					//					if (DEBUG) {
					//						FindbugsPlugin.getDefault().logError("NULL Classpath Entry for: "+res.getName());
					//					}
					continue; // ignore and continue
				}
				IPath outLocation = getAbsoluteOutputLocation(pkgRoot, cpe);

				// get the workspace relative path for this .java file
				IPath relativePath = getRelativeFilePath(res, cpe);
				IPath pkgPath = relativePath.removeLastSegments(1);
				String fName = relativePath.lastSegment();
				fName = fName.substring(0, fName.lastIndexOf('.'));
				// find the class and inner classes for this .java file
				IPath clzLocation = outLocation.append(pkgPath);
				String exp = fName+"\\.class"+"|"+fName+"\\$.*\\.class";
				File clzDir = clzLocation.toFile();
				// check if the directory exists in the output locations
				String oldExp = outputFiles.get(clzDir);
				if (oldExp != null) {
					exp = oldExp + "|" + exp;
				}

				pathConverter.put(relativePath.toString(), location.toOSString());
				outputFiles.put(clzDir, exp);
			}
		}

		// find and add all the class files in the output directories
		addOutputFiles(findbugsProject, outputFiles);
		// clear the map for GC
		outputFiles.clear();
		return findbugsProject;

	}

	/**
	 * Add the output .class files to the FindBugs project in the directories
	 * that match the corresponding patterns in the <code>Map</code> outputFiles.
	 *
	 * @param findBugsProject   findbugs <code>Project</code>
	 * @param outputFiles   Map containing output directories and patterns for .class files.
	 */
	private void addOutputFiles(Project findBugsProject, Map<File, String> outputFiles) {
		for (Map.Entry<File, String> entry: outputFiles.entrySet()) {
			File clzDir = entry.getKey();
			final Pattern pat = Pattern.compile(entry.getValue());
			if (clzDir.exists() && clzDir.isDirectory()) {
				File[] clzs = clzDir.listFiles(new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return pat.matcher(name).find();
					}
				});
				// add the clzs to the list of files to be analysed
				for (File cl: clzs) {
					findBugsProject.addFile(cl.getAbsolutePath());
				}
			}
		}
	}
	/**
	 * @param outputLocations
	 * @param path
	 * @return true if given path is a child of any one of path objects from given set
	 */
	private boolean containsIn(Set<IPath> outputLocations, IPath path){
		for (IPath dir : outputLocations) {
			if(dir.isPrefixOf(path)){
				return true;
			}
		}
		return false;
	}	


	/**
	 * @return set with IPath objects which represents all known output locations for
	 * current java project, never null
	 * @throws CoreException
	 */
	private Set<IPath> createOutputLocations() throws CoreException {
		Set<IPath> set = new HashSet<IPath>();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		// todo : ne pas prendre le premier : a voir
		//IProject[] projects = root.getProjects();
		IJavaProject javaProject = JavaCore.create(iProject);
		// path to the project without project name itself

		if (javaProject.exists() && javaProject.getProject().isOpen()) {
			IClasspathEntry entries[] = javaProject.getRawClasspath();
			for (int i = 0; i < entries.length; i++) {
				IClasspathEntry classpathEntry = entries[i];
				IPath path = classpathEntry.getOutputLocation();
				if (path != null && classpathEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					// this location is workspace relative and starts with project dir
					IResource resource = root.findMember(path);
					if (resource != null) {
						set.add(resource.getLocation());
					}
				}
			}
		}

		// add the default location
		IPath def = javaProject.getOutputLocation();

		IResource resource = root.findMember(def);
		if (resource != null) {
			IPath location = resource.getLocation();
			set.add(location);
		}

		return set;
	}


	/**
	 * transfert the findbugs report to the optet data model
	 * @param bugReporter the findbug report
	 */
	private void findbugsToOptet(BugReporter bugReporter, String evidenceName) {
		Iterator<BugInstance> bugIterator = bugReporter.getBugCollection().getCollection().iterator();
		// for each bug instance
		while(bugIterator.hasNext()){
			BugInstance instance = (BugInstance)bugIterator.next();

			// if the bug is containted into the preference bug category
			//if (userPrefCatAbbrev.contains(instance.getCategoryAbbrev()))
			{
				// add file marker
				//addMarker(getFile(pathConverter.get(instance.getPrimarySourceLineAnnotation().getSourcePath())), instance.getAbridgedMessage(), instance.getPrimarySourceLineAnnotation().getStartLine(), getSeverity(instance.getPriority()));

				// add new entry into the audit model
				System.out.println("adderror" + " findbugs " + evidenceName + " " + instance.getType());


				OptetDataModel.getInstance().addError(getFile(pathConverter.get(instance.getPrimarySourceLineAnnotation().getSourcePath())),
						"findbugs",
						instance.getPrimarySourceLineAnnotation().getStartLine(),
						getSeverity(instance.getPriority()),
						evidenceName,
						instance.getAbridgedMessage(),
						instance.getMessageWithPriorityType(),
						instance.getType());
			}
		}
		OrchestrationImpl.getInstance().staticAnalyseFinished(Activator.PLUGIN_ID);
	}

	/**
	 * getSeverity transalte the findbugs severity into the optet severity
	 * @param severity the findbugs severity
	 * @return the severity 
	 */
	private Severity getSeverity(int severity)
	{
		Severity sev = null;
		switch (severity) {
		case 1:
			sev = Severity.HIGH;
			break;
		case 2:
			sev = Severity.MEDIUM;
			break;
		case 3:
			sev = Severity.LOW;
			break;

		default:
			sev=Severity.HIGH;
			break;
		}

		return sev;
	}

	/**
	 * @param pkgRoot
	 * @param cpe
	 * @return
	 * @throws JavaModelException
	 */
	private IPath getAbsoluteOutputLocation(IPackageFragmentRoot pkgRoot, IClasspathEntry cpe) throws JavaModelException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();


		IPath outLocation = cpe.getOutputLocation();
		// check if it uses the default location
		IJavaProject proj = pkgRoot.getJavaProject();
		if (outLocation == null) {
			outLocation = proj.getOutputLocation();
		}
		IResource resource = root.findMember(outLocation);
		return resource.getLocation();
	}

	/**
	 * @param res
	 * @param cpe
	 * @return
	 */
	private IPath getRelativeFilePath(IResource res, IClasspathEntry cpe) {
		IPath cpePath = cpe.getPath();
		IPath javaFilePath = res.getFullPath();
		IPath relativePath = javaFilePath.removeFirstSegments(cpePath.matchingFirstSegments(javaFilePath));
		return relativePath;
	}

	@Override
	public void runMetric(IProject project) throws Exception {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see com.thalesgroup.optet.analysis.findbugs.AuditToolUtil#runRuntimeAnalysis()
	 */
	@Override
	public void runRuntimeAnalysis(IProject project) throws Exception {
		// TODO Auto-generated method stub

	}


}

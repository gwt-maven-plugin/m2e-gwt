/**
 * 
 */
package com.jeffmaury.m2e.gwt;

import static org.junit.Assert.*;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.m2e.tests.common.AbstractMavenProjectTestCase;
import org.junit.Test;

/**
 * @author jeffmaury
 *
 */
public class ImportProjectsTest extends AbstractMavenProjectTestCase {

  private IProject buildProject(String pomLocation) throws InterruptedException, CoreException, IOException {
    IProject project = super.importProject(pomLocation);
    waitForJobsToComplete(monitor);
    project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
    waitForJobsToComplete(monitor);
    return project;
  }

  /**
   * Return the computed classpath of the project.
   * 
   * @param project the project
   * @return the computed classpath
   * @throws JavaModelException
   */
  private IClasspathEntry[] getClasspath(IProject project) throws JavaModelException {
    IJavaProject javaProject = JavaCore.create(project);
    IClasspathEntry[] cp = javaProject.getRawClasspath();
    return cp;
  }
  
  private void assertSourcePathFound(IClasspathEntry[] cp, Path sourcePath) {
    boolean fndsw = false;
    for (IClasspathEntry entry : cp) {
      if (entry.getPath().equals(sourcePath)) {
        fndsw = true;
        break;
      }
    }
    assertTrue("Missing " + sourcePath.toString() + " source entry", fndsw);
  }
  
  @Test
  public void testBasicProjectFromArchetype() throws InterruptedException, CoreException, IOException {
    IProject project = buildProject("projects/project001/pom.xml");
    assertSourcePathFound(getClasspath(project), new Path("/project001/target/generated-sources/gwt"));
  }

  @Test
  public void testProjectWithAsyncSpecificDirectory() throws InterruptedException, CoreException, IOException {
    IProject project = buildProject("projects/project002/pom.xml");
    assertSourcePathFound(getClasspath(project), new Path("/project002/target/generated-sources/gwt"));
    assertSourcePathFound(getClasspath(project), new Path("/project002/target/generated-sources/gwt-async"));
  }

  @Test
  public void testProjectWithI18NSpecificDirectory() throws InterruptedException, CoreException, IOException {
    IProject project = buildProject("projects/project003/pom.xml");
    assertSourcePathFound(getClasspath(project), new Path("/project003/target/generated-sources/gwt"));
    assertSourcePathFound(getClasspath(project), new Path("/project003/target/generated-sources/gwt-i18n"));
  }
  
  @Test
  public void testProjectWithCSSSpecificDirectory() throws InterruptedException, CoreException, IOException {
    IProject project = buildProject("projects/project004/pom.xml");
    assertSourcePathFound(getClasspath(project), new Path("/project004/target/generated-sources/gwt"));
    assertSourcePathFound(getClasspath(project), new Path("/project004/target/generated-sources/gwt-css"));
  }
}

/*******************************************************************************
* Copyright 2011 Jeff MAURY
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
******************************************************************************/
package com.jeffmaury.m2e.gwt;

import java.io.File;
import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
import org.codehaus.plexus.util.Scanner;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;
import org.sonatype.plexus.build.incremental.BuildContext;

/**
 * @author jeffmaury
 *
 */
public class GWTBuildParticipant extends MojoExecutionBuildParticipant {
  public GWTBuildParticipant(MojoExecution execution) {
    super(execution, true);
  }

  /* (non-Javadoc)
   * @see org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant#build(int, org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  public Set<IProject> build(int kind, IProgressMonitor monitor)
      throws Exception {
    IMavenProjectFacade mavenProject = getMavenProjectFacade();
    IPath[] paths;
    BuildContext context = getBuildContext();
    String goal = getMojoExecution().getGoal();
    if ("generateAsync".equals(goal)) {
      paths = mavenProject.getCompileSourceLocations();
    } else {
      paths = mavenProject.getResourceLocations();
    }
    /*
     * look at source or resource folders to see if a file has been modified.
     * In the positive case, launch the mojo and refresh the generation
     * directory. In the negative, don't do anything.
     */
    //TODO: add some filters based on the mojo and mojo configuration
    boolean act = false;
    for(int i = 0; !act && i < paths.length;++i) {
      Scanner scanner = context.newScanner(mavenProject.getProject().getFolder(paths[i]).getLocation().toFile());
      scanner.scan();
      String[] includedFiles = scanner.getIncludedFiles();
      if (includedFiles != null && includedFiles.length > 0) {
        act = true;
      }
    }
    if (act) {
      Set<IProject> projects = super.build(kind, monitor);
      IMaven maven = MavenPlugin.getMaven();
      File generateDirectory = maven.getMojoParameterValue(getSession(),
          getMojoExecution(), GWTProjectConfigurator.GENERATE_DIRECTORY_PARAMETER_NAME, File.class);
      if (null != generateDirectory) {
        context.refresh(generateDirectory);
      }
      return projects;
    } else {
      return null;
    }
  }
  
  
}

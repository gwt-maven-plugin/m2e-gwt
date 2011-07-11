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

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator;

/**
 * @author jeffmaury
 * 
 */
public class GWTProjectConfigurator extends AbstractJavaProjectConfigurator {

  /**
   * Name of the target generation directory parameter for the Maven GWT goals
   * (i18n, css, generateAsync) that generates Java files.
   */
  public static final String GENERATE_DIRECTORY_PARAMETER_NAME = "generateDirectory";
  
	/**
	 * 
	 */
	public GWTProjectConfigurator() {
	}

  /* (non-Javadoc)
   * @see org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator#getOutputFolderParameterName()
   */
  @Override
  protected String getOutputFolderParameterName() {
    return GENERATE_DIRECTORY_PARAMETER_NAME;
  }

  /* (non-Javadoc)
   * @see org.eclipse.m2e.core.project.configurator.AbstractProjectConfigurator#getBuildParticipant(org.eclipse.m2e.core.project.IMavenProjectFacade, org.apache.maven.plugin.MojoExecution, org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata)
   */
  @Override
  public AbstractBuildParticipant getBuildParticipant(
      IMavenProjectFacade projectFacade, MojoExecution execution,
      IPluginExecutionMetadata executionMetadata) {
    return new GWTBuildParticipant(execution);
  }

     
}

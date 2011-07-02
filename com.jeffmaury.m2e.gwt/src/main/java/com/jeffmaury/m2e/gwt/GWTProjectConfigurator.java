/**
 * 
 */
package com.jeffmaury.m2e.gwt;

import org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator;

/**
 * @author jeffmaury
 * 
 */
public class GWTProjectConfigurator extends AbstractJavaProjectConfigurator {

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
    return "generateDirectory";
  }

}

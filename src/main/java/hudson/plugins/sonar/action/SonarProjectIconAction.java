/*
 * Jenkins Plugin for SonarQube, open source software quality management tool.
 * mailto:contact AT sonarsource DOT com
 *
 * Jenkins Plugin for SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Jenkins Plugin for SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package hudson.plugins.sonar.action;

import hudson.plugins.sonar.Messages;
import hudson.plugins.sonar.SonarPlugin;
import hudson.PluginWrapper;
import hudson.model.ProminentProjectAction;
import hudson.plugins.sonar.utils.SonarUtils;
import jenkins.model.Jenkins;

/**
 * {@link ProminentProjectAction} that allows user to go to the Sonar Dashboard.
 *
 * @author Evgeny Mandrikov
 * @since 1.2
 */
public final class SonarProjectIconAction implements ProminentProjectAction {

  private final SonarAnalysisAction buildInfo;
  private final String displayName;

  public SonarProjectIconAction() {
    this.buildInfo = null;
    this.displayName = Messages.SonarAction_Sonar();
  }

  public SonarProjectIconAction(SonarAnalysisAction buildInfo, boolean extended) {
    this.buildInfo = buildInfo;
    String projectName = extended ? SonarUtils.extractSonarProjectNameFromURL(buildInfo.getUrl()) : null;
    String suffix = projectName == null ? "" : (": " + projectName);
    this.displayName = Messages.SonarAction_Sonar() + suffix;
  }

  @Override
  public String getIconFileName() {
    PluginWrapper wrapper = Jenkins.getInstance().getPluginManager()
      .getPlugin(SonarPlugin.class);
    return "/plugin/" + wrapper.getShortName() + "/images/waves_48x48.png";
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  @Override
  public String getUrlName() {
    return buildInfo != null ? buildInfo.getUrl() : null;
  }

  public SonarAnalysisAction getBuildInfo() {
    return buildInfo;
  }
}

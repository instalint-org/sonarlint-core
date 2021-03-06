/*
 * SonarLint Core - Implementation
 * Copyright (C) 2009-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.sonarlint.core.container.analysis.issue.ignore.pattern;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.sonar.api.batch.fs.internal.PathPattern;
import org.sonar.api.utils.WildcardPattern;
import org.sonarsource.sonarlint.core.container.analysis.ExclusionFilters;

public class IssuePattern {

  private PathPattern pathPattern;
  private WildcardPattern rulePattern;

  public IssuePattern(String pathPattern, String rulePattern) {
    this.pathPattern = PathPattern.create(ExclusionFilters.convertToAbs(pathPattern));
    this.rulePattern = WildcardPattern.create(rulePattern);
  }

  public PathPattern getPathPattern() {
    return pathPattern;
  }

  public WildcardPattern getRulePattern() {
    return rulePattern;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

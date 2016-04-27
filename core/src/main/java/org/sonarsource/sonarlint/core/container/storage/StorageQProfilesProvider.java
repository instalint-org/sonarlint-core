/*
 * SonarLint Core - Implementation
 * Copyright (C) 2009-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
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
package org.sonarsource.sonarlint.core.container.storage;

import org.picocontainer.injectors.ProviderAdapter;
import org.sonarsource.sonarlint.core.container.connected.update.GlobalUpdateExecutor;
import org.sonarsource.sonarlint.core.proto.Sonarlint;
import org.sonarsource.sonarlint.core.proto.Sonarlint.ServerInfos;

public class StorageQProfilesProvider extends ProviderAdapter {

  private Sonarlint.QProfiles qProfilesFromStorage;

  public Sonarlint.QProfiles provide(StorageManager storageManager) {
    if (qProfilesFromStorage == null) {
      ServerInfos serverInfos = storageManager.readServerInfosFromStorage();
      boolean supportQualityProfilesWS = GlobalUpdateExecutor.supportQualityProfilesWS(serverInfos.getVersion());
      if (supportQualityProfilesWS) {
        qProfilesFromStorage = ProtobufUtil.readFile(storageManager.getQProfilesPath(), Sonarlint.QProfiles.parser());
      } else {
        qProfilesFromStorage = Sonarlint.QProfiles.getDefaultInstance();
      }
    }
    return qProfilesFromStorage;
  }
}

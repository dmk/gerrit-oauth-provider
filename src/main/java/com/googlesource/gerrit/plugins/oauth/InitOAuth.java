// Copyright (C) 2015 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.googlesource.gerrit.plugins.oauth;

import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.pgm.init.api.ConsoleUI;
import com.google.gerrit.pgm.init.api.InitStep;
import com.google.gerrit.pgm.init.api.Section;
import com.google.inject.Inject;

class InitOAuth implements InitStep {
  static final String PLUGIN_SECTION = "plugin";
  static final String CLIENT_ID = "client-id";
  static final String CLIENT_SECRET = "client-secret";
  static final String LINK_TO_EXISTING_OPENID_ACCOUNT = "link-to-existing-openid-accounts";
  static final String FIX_LEGACY_USER_ID = "fix-legacy-user-id";
  static final String DOMAIN = "domain";
  static final String USE_EMAIL_AS_USERNAME = "use-email-as-username";
  static final String ROOT_URL = "root-url";
  static final String REALM = "realm";
  static final String SERVICE_NAME = "service-name";
  static String FIX_LEGACY_USER_ID_QUESTION = "Fix legacy user id, without oauth provider prefix?";

  private final ConsoleUI ui;
  private final Section dexOAuthProviderSection;

  @Inject
  InitOAuth(ConsoleUI ui, Section.Factory sections, @PluginName String pluginName) {
    this.ui = ui;
    this.dexOAuthProviderSection =
        sections.get(PLUGIN_SECTION, pluginName + DexOAuthService.CONFIG_SUFFIX);
  }

  @Override
  public void run() throws Exception {
    ui.header("OAuth Authentication Provider");

	dexOAuthProviderSection.string("Dex Root URL", ROOT_URL, null);
    dexOAuthProviderSection.string("Application client id", CLIENT_ID, null);
    dexOAuthProviderSection.passwordForKey("Application client secret", CLIENT_SECRET);
  }

  @Override
  public void postRun() throws Exception {}
}

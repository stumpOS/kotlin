/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.plugin.runners

import org.jetbrains.kotlin.fir.plugin.FirAllOpenComponentRegistrar
import org.jetbrains.kotlin.fir.plugin.services.IrExtensionRegistrar
import org.jetbrains.kotlin.fir.plugin.services.PluginAnnotationsProvider
import org.jetbrains.kotlin.test.Constructor
import org.jetbrains.kotlin.test.builders.TestConfigurationBuilder
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives.ENABLE_PLUGIN_PHASES
import org.jetbrains.kotlin.test.directives.FirDiagnosticsDirectives.FIR_DUMP
import org.jetbrains.kotlin.test.frontend.fir.FirFrontendFacade
import org.jetbrains.kotlin.test.runners.baseFirDiagnosticTestConfiguration

fun TestConfigurationBuilder.commonFirWithPluginFrontendConfiguration() {
    baseFirDiagnosticTestConfiguration(frontendFacade = FirFrontendFacadeWithPlugin)

    defaultDirectives {
        +ENABLE_PLUGIN_PHASES
        +FIR_DUMP
    }

    useConfigurators(
        ::PluginAnnotationsProvider,
        ::IrExtensionRegistrar
    )
}

val FirFrontendFacadeWithPlugin: Constructor<FirFrontendFacade>
    get() = { testServices ->
        FirFrontendFacade(testServices) {
            it.registerExtensions(FirAllOpenComponentRegistrar().configure())
        }
    }

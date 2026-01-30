package app.supersslc.ui.preferences.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import app.supersslc.backup.ui.CreateBackupScreen
import app.supersslc.backup.ui.restoreBackupGraph
import app.supersslc.preferences.BasePreferenceManager
import app.supersslc.preferences.preferenceManager
import app.supersslc.ui.preferences.LocalIsExpandedScreen
import app.supersslc.ui.preferences.about.About
import app.supersslc.ui.preferences.about.acknowledgements.Acknowledgements
import app.supersslc.ui.preferences.components.colorpreference.ColorPreferenceModelList
import app.supersslc.ui.preferences.components.colorpreference.ColorSelection
import app.supersslc.ui.preferences.components.search.SearchProviderPreferenceScreen
import app.supersslc.ui.preferences.destinations.AppDrawerFoldersPreference
import app.supersslc.ui.preferences.destinations.AppDrawerPreferences
import app.supersslc.ui.preferences.destinations.CustomIconShapePreference
import app.supersslc.ui.preferences.destinations.DebugMenuPreferences
import app.supersslc.ui.preferences.destinations.DockPreferences
import app.supersslc.ui.preferences.destinations.DummyPreference
import app.supersslc.ui.preferences.destinations.ExperimentalFeaturesPreferences
import app.supersslc.ui.preferences.destinations.FeatureFlagsPreference
import app.supersslc.ui.preferences.destinations.FolderPreferences
import app.supersslc.ui.preferences.destinations.FontSelection
import app.supersslc.ui.preferences.destinations.GeneralPreferences
import app.supersslc.ui.preferences.destinations.GesturePreferences
import app.supersslc.ui.preferences.destinations.HiddenAppsPreferences
import app.supersslc.ui.preferences.destinations.HomeScreenGridPreferences
import app.supersslc.ui.preferences.destinations.HomeScreenPreferences
import app.supersslc.ui.preferences.destinations.IconPackPreferences
import app.supersslc.ui.preferences.destinations.IconPickerPreference
import app.supersslc.ui.preferences.destinations.IconShapePreference
import app.supersslc.ui.preferences.destinations.LauncherPopupPreference
import app.supersslc.ui.preferences.destinations.PickAppForGesture
import app.supersslc.ui.preferences.destinations.PreferencesDashboard
import app.supersslc.ui.preferences.destinations.QuickstepPreferences
import app.supersslc.ui.preferences.destinations.SearchPreferences
import app.supersslc.ui.preferences.destinations.SearchProviderPreferences
import app.supersslc.ui.preferences.destinations.SelectAppsForDrawerFolder
import app.supersslc.ui.preferences.destinations.SelectIconPreference
import app.supersslc.ui.preferences.destinations.ShapePreference
import app.supersslc.ui.preferences.destinations.SmartspacePreferences
import com.android.launcher3.util.ComponentKey
import soup.compose.material.motion.animation.materialSharedAxisXIn
import soup.compose.material.motion.animation.materialSharedAxisXOut
import soup.compose.material.motion.animation.rememberSlideDistance

@Composable
fun PreferenceNavigation(
    navController: NavHostController,
    startDestination: PreferenceRoute,
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    val slideDistance = rememberSlideDistance()

    // TODO: navigate to nav3: https://developer.android.com/guide/navigation/navigation-3
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { materialSharedAxisXIn(!isRtl, slideDistance) },
        exitTransition = { materialSharedAxisXOut(!isRtl, slideDistance) },
        popEnterTransition = { materialSharedAxisXIn(isRtl, slideDistance) },
        popExitTransition = { materialSharedAxisXOut(isRtl, slideDistance) },
    ) {
        composable<Root> {
            val isExpandedScreen = LocalIsExpandedScreen.current

            PreferencesDashboard(
                currentRoute = Root,
                onNavigate = {
                    navController.navigate(it)
                },
            )

            LaunchedEffect(isExpandedScreen) {
                if (isExpandedScreen) {
                    navController.navigate(General) {
                        launchSingleTop = true
                        popUpTo(navController.graph.id)
                    }
                }
            }
        }
        composable<Dummy> {
            DummyPreference()
        }

        composable<General> { GeneralPreferences() }
        composable<GeneralFontSelection> { backStackEntry ->
            val route: GeneralFontSelection = backStackEntry.toRoute()
            val pref = preferenceManager().prefsMap[route.prefKey]
                as? BasePreferenceManager.FontPref ?: return@composable
            FontSelection(pref)
        }
        composable<GeneralIconPack> { IconPackPreferences() }
        composable<GeneralIconShape> { IconShapePreference() }
        composable<GeneralIconShape> { backStackEntry ->
            val route: GeneralIconShape = backStackEntry.toRoute()
            ShapePreference(currentTab = route.selectedId)
        }
        composable<GeneralCustomIconShapeCreator> { CustomIconShapePreference() }

        composable<HomeScreen> { HomeScreenPreferences() }
        composable<HomeScreenGrid> { HomeScreenGridPreferences() }
        composable<HomeScreenPopupEditor> { LauncherPopupPreference() }

        composable<Dock> { DockPreferences() }
        composable<DockSearchProvider> { SearchProviderPreferences() }

        composable<Smartspace> { SmartspacePreferences(fromWidget = false) }
        composable<SmartspaceWidget> { SmartspacePreferences(fromWidget = true) }

        composable<AppDrawer> { AppDrawerPreferences() }
        composable<AppDrawerHiddenApps> { HiddenAppsPreferences() }
        composable<AppDrawerAppListToFolder> { backStackEntry ->
            val args = backStackEntry.arguments!!
            val folderInfoId = args.getInt("id")
            SelectAppsForDrawerFolder(folderInfoId)
        }
        composable<AppDrawerFolder> { AppDrawerFoldersPreference() }

        composable<Search> { backStackEntry ->
            val route: Search = backStackEntry.toRoute()
            SearchPreferences(currentTab = route.selectedId)
        }
        composable<SearchProviderPreference> { backStackEntry ->
            val route: SearchProviderPreference = backStackEntry.toRoute()
            SearchProviderPreferenceScreen(route.id)
        }

        composable<Folders> { FolderPreferences() }

        composable<Gestures> { GesturePreferences() }
        composable<GesturesPickApp> { PickAppForGesture() }

        composable<Quickstep> { QuickstepPreferences() }

        composable<About> { About() }
        composable<AboutLicenses> { Acknowledgements() }

        composable<DebugMenu> { DebugMenuPreferences() }
        composable<FeatureFlags> { FeatureFlagsPreference() }

        composable<SelectIcon> { backStackEntry ->
            val args: SelectIcon = backStackEntry.toRoute()
            val componentKey = args.componentKey
            val key = ComponentKey.fromString(componentKey)!!
            SelectIconPreference(key)
        }
        composable<IconPicker> { backStackEntry ->
            val args: IconPicker = backStackEntry.toRoute()
            IconPickerPreference(packageName = args.packageName)
        }

        composable<ExperimentalFeatures> { ExperimentalFeaturesPreferences() }
        composable<ColorSelection> { backStackEntry ->
            val screen: ColorSelection = backStackEntry.toRoute()
            val modelList = ColorPreferenceModelList.INSTANCE.get(LocalContext.current)
            val model = modelList[screen.prefKey]
            ColorSelection(
                label = stringResource(id = model.labelRes),
                preference = model.prefObject,
                dynamicEntries = model.dynamicEntries,
            )
        }

        composable<CreateBackup> { CreateBackupScreen(viewModel()) }

        restoreBackupGraph()
    }
}

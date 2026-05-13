package com.bright.beepower.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bright.beepower.data.UserSession
import com.bright.beepower.ui.screens.*
import com.bright.beepower.ui.screens.auth.*
import com.bright.beepower.ui.screens.buytoken.BuyTokenScreen
import com.bright.beepower.ui.screens.dashboard.DashBoardScreen
import com.bright.beepower.ui.screens.onboarding.OnBoardingScreen
import com.bright.beepower.ui.screens.profile.ProfileScreen
import com.bright.beepower.ui.screens.report.*
import com.bright.beepower.ui.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    val isAdmin = (UserSession.role ?: "user") == "admin"   // ✅ ADD THIS

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUT_ONBOARDING) {
            OnBoardingScreen(navController)
        }

        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUT_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUT_DASHBOARD) {
            DashBoardScreen(navController)
        }

        composable(ROUT_BUYTOKEN) {
            BuyTokenScreen(navController)
        }

        composable(ROUT_HISTORY) {
            HistoryScreen(navController)
        }

        composable(ROUT_NOTIFICATION) {
            NotificationScreen(navController)
        }

        composable(ROUT_PROFILE) {
            ProfileScreen(navController)
        }

        composable(ROUT_REPORT_ISSUE) {
            ReportIssueScreen(navController)
        }

        // 🔒 ADMIN PROTECTED SCREEN
        composable(ROUT_VIEW_REPORTS) {

            if (isAdmin) {
                ViewReportScreen(navController)
            } else {
                // redirect normal users
                DashBoardScreen(navController)
            }
        }
    }
}
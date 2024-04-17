package com.example.rally

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rally.ui.accounts.AccountsScreen
import com.example.rally.ui.accounts.SingleAccountScreen
import com.example.rally.ui.bills.BillsScreen
import com.example.rally.ui.overview.OverviewScreen

@Composable
fun RallyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ){
        composable(route = Overview.route){
            OverviewScreen(
                onClickSeeAllAccounts = {
                    navController.navigatesSingleTopTo(Accounts.route)
                },
                onClickSeeAllBills = {
                    navController.navigatesSingleTopTo(Bills.route)
                },
                onAccountClick = {accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(route = Accounts.route){
            AccountsScreen(
                onAccountClick = {accountType ->
                    navController.navigateToSingleAccount(accountType)
                }
            )
        }
        composable(route = Bills.route){
            BillsScreen()
        }
        composable(
            route = SingleAccount.routeWithArgs,
            arguments = SingleAccount.arguments,
            deepLinks = SingleAccount.deepLinks
        ){navBackStackEntry ->
            val accountType =
                navBackStackEntry.arguments?.getString(SingleAccount.accountTypeArg)
            SingleAccountScreen(accountType)
        }
    }
}
fun NavHostController.navigatesSingleTopTo(route:String) =
    this.navigate(route){
        popUpTo(
            this@navigatesSingleTopTo.graph.findStartDestination().id
        ){
            saveState=true
        }
        launchSingleTop= true
        restoreState= true
    }
private fun NavHostController.navigateToSingleAccount(accountType:String){
    this.navigatesSingleTopTo("${SingleAccount.route}/$accountType")
}
package com.monmenusante.algerie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Food(val name: String, val amount: String)
data class Meal(val title: String, val foods: List<Food>, val prep: String)

data class DayMenu(val day: String, val fasting: Boolean, val meals: List<Meal>)

private val weeklyMenus = listOf(
    DayMenu("Samedi", false, listOf(
        Meal("Déjeuner · 13 h", listOf(Food("Blanc de dinde grillé","180 g"),Food("Œufs durs","2"),Food("Pain complet","80 g"),Food("Salade composée","200 g"),Food("Huile d’olive","10 g"),Food("Yaourt nature","125 g"),Food("Pomme","1 moyenne")), "Griller la dinde. Servir avec les œufs, la salade et le pain complet."),
        Meal("Dîner · 19 h — Lentilles", listOf(Food("Lentilles cuites","250 g"),Food("Blanc de dinde","150 g"),Food("Fromage blanc","150 g"),Food("Légumes cuits","200 g"),Food("Pain complet","60 g"),Food("Huile d’olive","10 g")), "Cuire les lentilles avec carotte, oignon, ail et cumin. Ajouter la dinde et laisser mijoter.")
    )),
    DayMenu("Dimanche", false, listOf(
        Meal("Déjeuner · 13 h", listOf(Food("Œufs","3"),Food("Fromage blanc","200 g"),Food("Pain complet","100 g"),Food("Salade de légumes","200 g"),Food("Huile d’olive","10 g"),Food("Amandes non salées","15 g"),Food("Orange","1")), "Cuire les œufs et accompagner de crudités."),
        Meal("Dîner · 19 h — Couscous à la dinde", listOf(Food("Couscous complet (sec)","90 g"),Food("Dinde cuite","180 g"),Food("Pois chiches cuits","80 g"),Food("Carotte","100 g"),Food("Courgette","100 g"),Food("Navet","50 g"),Food("Huile d’olive","10 g"),Food("Yaourt nature","125 g")), "Préparer un bouillon léger avec la dinde, les légumes et les pois chiches. Cuire le couscous à la vapeur. Limiter le sel.")
    )),
    DayMenu("Lundi", true, listOf(
        Meal("Repas unique après le coucher du soleil", listOf(Food("Frik (sec)","50 g"),Food("Dinde cuite","200 g"),Food("Carotte","75 g"),Food("Courgette","75 g"),Food("Navet","50 g"),Food("Huile d’olive","10 g"),Food("Œufs","2"),Food("Fromage blanc","200 g"),Food("Pain complet","60 g"),Food("Banane","1"),Food("Amandes non salées","15 g")), "Préparer une chorba frik avec oignon, céleri, coriandre, légumes et dinde. Ajouter le frik. Sans concentré de tomate.")
    )),
    DayMenu("Mardi", false, listOf(
        Meal("Déjeuner · 13 h", listOf(Food("Blanc de dinde","180 g"),Food("Œufs","2"),Food("Pain complet","80 g"),Food("Fromage blanc","100 g"),Food("Salade","200 g"),Food("Huile d’olive","10 g"),Food("Fruit","1")), "Griller la dinde et servir avec les œufs et la salade."),
        Meal("Dîner · 19 h — Tlitli", listOf(Food("Tlitli (sec)","90 g"),Food("Dinde","150 g"),Food("Légumes","250 g"),Food("Fromage blanc","150 g"),Food("Huile d’olive","10 g"),Food("Yaourt nature","125 g")), "Cuire le tlitli. Préparer une sauce légère avec dinde, courgettes, carottes et oignons.")
    )),
    DayMenu("Mercredi", false, listOf(
        Meal("Déjeuner · 13 h", listOf(Food("Dinde grillée","180 g"),Food("Pain complet","100 g"),Food("Fromage blanc","150 g"),Food("Crudités","200 g"),Food("Huile d’olive","10 g"),Food("Amandes","15 g"),Food("Fruit","1")), "Griller la dinde et servir avec crudités et pain complet."),
        Meal("Dîner · 19 h — Berkoukes", listOf(Food("Berkoukes (sec)","80 g"),Food("Dinde","150 g"),Food("Pois chiches cuits","80 g"),Food("Carotte","100 g"),Food("Courgette","100 g"),Food("Navet","50 g"),Food("Huile d’olive","10 g"),Food("Yaourt nature","125 g")), "Préparer un bouillon avec dinde, légumes et pois chiches. Ajouter les grains de berkoukes.")
    )),
    DayMenu("Jeudi", true, listOf(
        Meal("Repas unique après le coucher du soleil", listOf(Food("Couscous complet (sec)","90 g"),Food("Dinde cuite","220 g"),Food("Œufs","2"),Food("Fromage blanc","200 g"),Food("Carotte","100 g"),Food("Courgette","100 g"),Food("Navet","100 g"),Food("Huile d’olive","15 g"),Food("Fruit","1"),Food("Amandes non salées","20 g")), "Cuire la dinde avec les légumes et les épices douces. Préparer le couscous à la vapeur."),
    )),
    DayMenu("Vendredi", false, listOf(
        Meal("Déjeuner · 13 h", listOf(Food("Dinde grillée","180 g"),Food("Œufs","2"),Food("Pain complet","80 g"),Food("Fromage blanc","100 g"),Food("Salade","200 g"),Food("Huile d’olive","10 g"),Food("Pomme ou orange","1")), "Griller la dinde et servir avec les œufs et la salade."),
        Meal("Dîner · 19 h — Pois chiches", listOf(Food("Pois chiches cuits","250 g"),Food("Dinde","120 g"),Food("Fromage blanc","150 g"),Food("Légumes","200 g"),Food("Pain complet","50 g"),Food("Huile d’olive","10 g")), "Cuire les pois chiches avec carotte, courgette, oignon, ail, cumin et coriandre. Ajouter la dinde.")
    ))
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MenuSanteApp() }
    }
}

@Composable
fun MenuSanteApp() {
    var selectedDay by remember { mutableIntStateOf(0) }
    var page by remember { mutableStateOf("jour") }
    var dark by remember { mutableStateOf(false) }
    var generated by remember { mutableIntStateOf(0) }

    val bg = if (dark) Color(0xFF101827) else Color(0xFFF3F7F4)
    val ink = if (dark) Color(0xFFEAF2F0) else Color(0xFF17352C)
    val green = Color(0xFF167A58)
    MaterialTheme(colorScheme = if (dark) darkColorScheme(primary=Color(0xFF67D6A6), background=bg, surface=Color(0xFF1B2938)) else lightColorScheme(primary=green, background=bg, surface=Color.White)) {
        Scaffold(containerColor=bg, bottomBar={
            NavigationBar(containerColor=if(dark) Color(0xFF1B2938) else Color.White) {
                NavigationBarItem(selected=page=="jour", onClick={page="jour"}, icon={Icon(Icons.Default.Restaurant,null)}, label={Text("Menu du jour")})
                NavigationBarItem(selected=page=="semaine", onClick={page="semaine"}, icon={Icon(Icons.Default.CalendarMonth,null)}, label={Text("Semaine")})
                NavigationBarItem(selected=page=="conseils", onClick={page="conseils"}, icon={Icon(Icons.Default.Favorite,null)}, label={Text("Repères")})
            }
        }) { padding ->
            LazyColumn(modifier=Modifier.fillMaxSize().padding(padding).padding(horizontal=16.dp), verticalArrangement=Arrangement.spacedBy(14.dp), contentPadding=PaddingValues(top=18.dp,bottom=24.dp)) {
                item {
                    Row(verticalAlignment=Alignment.CenterVertically, horizontalArrangement=Arrangement.SpaceBetween, modifier=Modifier.fillMaxWidth()) {
                        Column {
                            Text("MON MENU SANTÉ", color=ink, fontSize=22.sp, fontWeight=FontWeight.Black)
                            Text("Cuisine algérienne · Objectif équilibre", color=ink.copy(alpha=.7f), fontSize=12.sp)
                        }
                        IconButton(onClick={dark=!dark}) { Icon(if(dark) Icons.Default.LightMode else Icons.Default.DarkMode, "Changer de thème", tint=ink) }
                    }
                }
                when(page) {
                    "jour" -> {
                        item {
                            Text("Choisissez votre journée", color=ink, fontWeight=FontWeight.Bold)
                            Row(horizontalArrangement=Arrangement.spacedBy(6.dp), modifier=Modifier.fillMaxWidth()) {
                                weeklyMenus.forEachIndexed { i, d ->
                                    FilterChip(selected=selectedDay==i, onClick={selectedDay=i}, label={Text(d.day.take(2))})
                                }
                            }
                        }
                        item {
                            val day=weeklyMenus[selectedDay]
                            Surface(shape=RoundedCornerShape(24.dp), color=if(dark) Color(0xFF1B2938) else Color.White, shadowElevation=8.dp, modifier=Modifier.fillMaxWidth()) {
                                Column(Modifier.padding(18.dp), verticalArrangement=Arrangement.spacedBy(10.dp)) {
                                    Text(day.day.uppercase(), color=green, fontWeight=FontWeight.ExtraBold, fontSize=19.sp)
                                    if(day.fasting) Text("JEÛNE · UN SEUL REPAS · APRÈS LE COUCHER DU SOLEIL", color=Color(0xFFC47A22), fontWeight=FontWeight.Bold, fontSize=12.sp)
                                    day.meals.forEach { MealCard(it, ink) }
                                }
                            }
                        }
                        item {
                            Button3D("✨  GÉNÉRER UN MENU", onClick={ selectedDay=(selectedDay+1)%7; generated++ }, green)
                            Text(if(generated>0) "Suggestion suivante affichée. Les menus sont issus de votre programme hebdomadaire." else "Appuyez pour passer à une autre suggestion du programme.", color=ink.copy(alpha=.65f), fontSize=12.sp)
                        }
                    }
                    "semaine" -> item {
                        Text("Programme hebdomadaire", color=ink, fontSize=20.sp, fontWeight=FontWeight.Bold)
                        weeklyMenus.forEachIndexed { i, d ->
                            CardMenu(d, ink, onClick={selectedDay=i; page="jour"})
                        }
                    }
                    else -> item {
                        Text("Vos repères personnels", color=ink, fontSize=20.sp, fontWeight=FontWeight.Bold)
                        InfoCard("Profil de départ", "65 ans · 182 cm · 98 kg\nObjectif initial : 92 kg\nPoids à suivre régulièrement.")
                        InfoCard("Ordre du repas", "1. Légumes et crudités\n2. Protéines\n3. Féculents avec le reste du repas\nCette organisation est facultative : l’équilibre global et les portions comptent davantage.")
                        InfoCard("Hydratation et sécurité", "Boire régulièrement les jours sans jeûne. Le jeûne sans eau associé à de longues marches et au telmisartan nécessite un avis médical. Ne modifiez pas votre traitement seul. En cas de malaise, vertiges ou faiblesse inhabituelle, interrompez l’effort et demandez un avis médical.")
                        InfoCard("Préférences alimentaires", "Dinde, œufs, fromage blanc, légumes, légumineuses et plats algériens. Pas de poisson. Chorba frik sans concentré de tomate.")
                    }
                }
            }
        }
    }
}

@Composable
fun MealCard(meal: Meal, ink: Color) {
    Column(verticalArrangement=Arrangement.spacedBy(6.dp)) {
        Text(meal.title, color=ink, fontWeight=FontWeight.Bold, fontSize=16.sp)
        meal.foods.forEach { f ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.SpaceBetween) {
                Text("• ${f.name}", color=ink.copy(alpha=.88f), fontSize=13.sp)
                Text(f.amount, color=ink, fontWeight=FontWeight.SemiBold, fontSize=13.sp)
            }
        }
        Text("Préparation : ${meal.prep}", color=ink.copy(alpha=.72f), fontSize=12.sp)
        HorizontalDivider()
    }
}

@Composable
fun Button3D(label: String, onClick: () -> Unit, color: Color) {
    Column {
        Surface(color=color.copy(alpha=.45f), shape=RoundedCornerShape(18.dp), modifier=Modifier.fillMaxWidth().height(58.dp).offset(y=4.dp)) {}
        Button(onClick=onClick, modifier=Modifier.fillMaxWidth().height(58.dp).offset(y=(-4).dp).shadow(8.dp, RoundedCornerShape(18.dp)), shape=RoundedCornerShape(18.dp), colors=ButtonDefaults.buttonColors(containerColor=color), elevation=ButtonDefaults.buttonElevation(defaultElevation=7.dp, pressedElevation=1.dp)) {
            Text(label, fontWeight=FontWeight.Black, letterSpacing=1.sp)
        }
    }
}

@Composable
fun CardMenu(day: DayMenu, ink: Color, onClick: () -> Unit) {
    Card(onClick=onClick, modifier=Modifier.fillMaxWidth(), shape=RoundedCornerShape(18.dp), elevation=CardDefaults.cardElevation(defaultElevation=4.dp)) {
        Row(Modifier.padding(16.dp), verticalAlignment=Alignment.CenterVertically, horizontalArrangement=Arrangement.SpaceBetween) {
            Column {
                Text(day.day, color=ink, fontWeight=FontWeight.Bold)
                Text(if(day.fasting) "Jeûne · repas unique" else "Déjeuner et dîner", color=ink.copy(alpha=.65f), fontSize=12.sp)
            }
            Icon(Icons.Default.ChevronRight, null)
        }
    }
}

@Composable
fun InfoCard(title: String, body: String) {
    Card(modifier=Modifier.fillMaxWidth(), shape=RoundedCornerShape(18.dp), elevation=CardDefaults.cardElevation(defaultElevation=3.dp)) {
        Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
            Text(title, fontWeight=FontWeight.Bold, fontSize=16.sp, color=MaterialTheme.colorScheme.primary)
            Text(body, fontSize=14.sp, lineHeight=20.sp)
        }
    }
}

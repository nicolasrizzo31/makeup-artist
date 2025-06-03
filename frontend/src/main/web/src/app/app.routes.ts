import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home.component';
import { ChiSonoComponent } from './pages/chi-sono.component';
import { PortfolioComponent } from './pages/portfolio.component';
import { ServiziComponent } from './pages/servizi.component';
import { PrenotazioniComponent } from './pages/prenotazioni.component';
import { RecensioniComponent } from './pages/recensioni.component';
import { ContattiComponent } from './pages/contatti.component';

export const routes: Routes = [
    { path: '', component: HomeComponent, pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'chi-sono', component: ChiSonoComponent },
    { path: 'portfolio', component: PortfolioComponent },
    { path: 'servizi', component: ServiziComponent },
    { path: 'prenotazioni', component: PrenotazioniComponent },
    { path: 'recensioni', component: RecensioniComponent },
    { path: 'contatti', component: ContattiComponent },
    // Optional: Redirect to home or a 404 component for unknown paths
    { path: '**', redirectTo: '', pathMatch: 'full' }
];

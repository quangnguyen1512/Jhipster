import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'area',
        loadChildren: () => import('./area/area.module').then(m => m.GssgnAreaModule),
      },
      {
        path: 'control',
        loadChildren: () => import('./control/control.module').then(m => m.GssgnControlModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class GssgnEntityModule {}

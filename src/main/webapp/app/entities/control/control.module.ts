import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GssgnSharedModule } from '../../shared/shared.module';
import { controlRoute } from './control.route';
import { ControlComponent } from './control.component';
import { ControlDetailComponent } from './control-detail.component';
import { ControlDeleteDialogComponent } from './control-delete-dialog.component';
import { ControlUpdateComponent } from './control-update.component';

@NgModule({
  imports: [GssgnSharedModule, RouterModule.forChild(controlRoute)],
  declarations: [ControlComponent, ControlDetailComponent, ControlDeleteDialogComponent, ControlUpdateComponent],
  entryComponents: [ControlDeleteDialogComponent],
})
export class GssgnControlModule {}

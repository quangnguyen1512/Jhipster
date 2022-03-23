import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { IControl } from '../../shared/model/control.model';
import { ControlService } from './control.service';

@Component({
  templateUrl: './control-delete-dialog.component.html',
})
export class ControlDeleteDialogComponent {
  control?: IControl;

  constructor(protected controlService: ControlService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.controlService.delete(id).subscribe(() => {
      this.eventManager.broadcast('areaListModification');
      this.activeModal.close();
    });
  }
}

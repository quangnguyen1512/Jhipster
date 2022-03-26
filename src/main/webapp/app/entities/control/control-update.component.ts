import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { ControlService } from './control.service';
import { Control, IControl } from '../../shared/model/control.model';

@Component({
  selector: 'jhi-control-update',
  templateUrl: './control-update.component.html',
})
export class ControlUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    controlCode: [],
    controlName: [],
    regionCode: [],
    status: [],
  });

  constructor(protected controlService: ControlService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ control }) => {
      if (!control.id) {
        const today = moment().startOf('day');
      }

      this.updateForm(control);
    });
  }

  updateForm(control: IControl): void {
    this.editForm.patchValue({
      id: control.id,
      controlCode: control.controlCode,
      controlName: control.controlName,
      regionCode: control.regionCode,
      status: control.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const control = this.createFromForm();
    if (control.id !== undefined) {
      this.subscribeToSaveResponse(this.controlService.update(control));
    } else {
      this.subscribeToSaveResponse(this.controlService.create(control));
    }
  }

  private createFromForm(): IControl {
    return {
      ...new Control(),
      id: this.editForm.get(['id'])!.value,
      controlCode: this.editForm.get(['controlCode'])!.value,
      controlName: this.editForm.get(['controlName'])!.value,
      regionCode: this.editForm.get(['regionCode'])!.value,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IControl>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}

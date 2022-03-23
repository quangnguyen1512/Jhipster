import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, ParamMap, Router, Data } from '@angular/router';
import { Subscription, combineLatest } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { IControl } from '../../shared/model/control.model';
import { ControlService } from './control.service';
import { ControlDeleteDialogComponent } from './control-delete-dialog.component';

@Component({
  selector: 'jhi-control',
  templateUrl: './control.component.html',
})
export class ControlComponent implements OnInit, OnDestroy {
  controls?: IControl[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected controlService: ControlService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number, dontNavigate?: boolean): void {
    console.log('X1');
    const pageToLoad: number = page || this.page || 1;

    this.controlService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IControl[]>) => this.onSuccess(res.body, res.headers, pageToLoad, !dontNavigate),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    console.log('X2');
    this.handleNavigation();
    this.registerChangeInControls();
  }

  protected handleNavigation(): void {
    console.log('X3');
    combineLatest(this.activatedRoute.data, this.activatedRoute.queryParamMap, (data: Data, params: ParamMap) => {
      const page = params.get('page');
      const pageNumber = page !== null ? +page : 1;
      const sort = (params.get('sort') ?? data['defaultSort']).split(',');
      const predicate = sort[0];
      const ascending = sort[1] === 'asc';
      if (pageNumber !== this.page || predicate !== this.predicate || ascending !== this.ascending) {
        this.predicate = predicate;
        this.ascending = ascending;
        this.loadPage(pageNumber, true);
      }
    }).subscribe();
  }

  ngOnDestroy(): void {
    console.log('X4');
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IControl): number {
    console.log('X5');
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInControls(): void {
    console.log('X6');
    this.eventSubscriber = this.eventManager.subscribe('controlListModification', () => this.loadPage());
  }

  delete(control: IControl): void {
    console.log('X7');
    const modalRef = this.modalService.open(ControlDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.control = control;
  }

  sort(): string[] {
    console.log('X8');
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IControl[] | null, headers: HttpHeaders, page: number, navigate: boolean): void {
    console.log('X9');
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    if (navigate) {
      this.router.navigate(['/control'], {
        queryParams: {
          page: this.page,
          size: this.itemsPerPage,
          sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc'),
        },
      });
    }
    this.controls = data || [];
    this.ngbPaginationPage = this.page;
  }

  protected onError(): void {
    console.log('X10');
    this.ngbPaginationPage = this.page ?? 1;
  }
}

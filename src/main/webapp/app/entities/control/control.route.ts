import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';
import { Control, IControl } from '../../shared/model/control.model';
import { ControlService } from './control.service';
import { Authority } from '../../shared/constants/authority.constants';
import { UserRouteAccessService } from '../../core/auth/user-route-access-service';
import { ControlComponent } from './control.component';
import { ControlDetailComponent } from './control-detail.component';
import { ControlUpdateComponent } from './control-update.component';

@Injectable({ providedIn: 'root' })
export class ControlResolve implements Resolve<IControl> {
  constructor(private service: ControlService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IControl> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((control: HttpResponse<Control>) => {
          if (control.body) {
            return of(control.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Control());
  }
}
export const controlRoute: Routes = [
  {
    path: '',
    component: ControlComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'gssgnApp.control.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ControlDetailComponent,
    resolve: {
      control: ControlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gssgnApp.control.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ControlUpdateComponent,
    resolve: {
      control: ControlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gssgnApp.control.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ControlUpdateComponent,
    resolve: {
      control: ControlResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'gssgnApp.control.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

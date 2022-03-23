import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IControl } from '../../shared/model/control.model';

type EntityResponseType = HttpResponse<IControl>;
type EntityArrayResponseType = HttpResponse<IControl[]>;

@Injectable({ providedIn: 'root' })
export class ControlService {
  public resourceUrl = SERVER_API_URL + 'api/controls';

  constructor(protected http: HttpClient) {}

  create(control: IControl): Observable<EntityResponseType> {
    console.log('create');
    const copy = this.convertDateFromClient(control);
    return this.http
      .post<IControl>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(control: IControl): Observable<EntityResponseType> {
    console.log('update1');
    const copy = this.convertDateFromClient(control);
    return this.http
      .put<IControl>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IControl>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IControl[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(control: IControl): IControl {
    const copy: IControl = Object.assign({}, control, {
      createdDate: control.createdDate && control.createdDate.isValid() ? control.createdDate.toJSON() : undefined,
      updatedDate: control.updatedDate && control.updatedDate.isValid() ? control.updatedDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.updatedDate = res.body.updatedDate ? moment(res.body.updatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((control: IControl) => {
        control.createdDate = control.createdDate ? moment(control.createdDate) : undefined;
        control.updatedDate = control.updatedDate ? moment(control.updatedDate) : undefined;
      });
    }
    return res;
  }
}

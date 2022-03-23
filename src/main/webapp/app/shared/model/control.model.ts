import { CommonStatus } from 'app/shared/model/enumerations/common-status.model';
import { Moment } from 'moment';

export interface IControl {
  id?: number;
  controlCode?: string;
  controlName?: string;
  regionCode?: string;
  status?: CommonStatus;
  createdDate?: Moment;
  createdBy?: string;
  updatedDate?: Moment;
  updatedBy?: string;
}

export class Control implements IControl {
  constructor(
    public id?: number,
    public controlCode?: string,
    public controlName?: string,
    public regionCode?: string,
    public status?: CommonStatus,
    public createdDate?: Moment,
    public createdBy?: string,
    public updatedDate?: Moment,
    public updatedBy?: string
  ) {}
}

import dayjs from 'dayjs';

export interface IFimCustWq {
  id?: number;
  custId?: string | null;
  clientId?: string;
  idType?: string;
  ctryCode?: string;
  createdBy?: string;
  createdTs?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedTs?: dayjs.Dayjs | null;
  recordStatus?: string | null;
  uploadRemark?: string | null;
}

export const defaultValue: Readonly<IFimCustWq> = {};

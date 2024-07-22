import dayjs from 'dayjs';

export interface IFimCustHistory {
  id?: number;
  custId?: string | null;
  historyTs?: dayjs.Dayjs | null;
  clientId?: string;
  idType?: string;
  ctryCode?: string;
  createdBy?: string;
  createdTs?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedTs?: dayjs.Dayjs | null;
  recordStatus?: string | null;
}

export const defaultValue: Readonly<IFimCustHistory> = {};

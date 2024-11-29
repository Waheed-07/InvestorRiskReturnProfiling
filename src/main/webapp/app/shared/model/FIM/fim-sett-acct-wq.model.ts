import dayjs from 'dayjs';

export interface IFimSettAcctWq {
  id?: number;
  settaccId?: string | null;
  accountId?: string;
  settAcctNbr?: string;
  settCcy?: string;
  settAcctStatus?: string;
  createdBy?: string | null;
  createdTs?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedTs?: dayjs.Dayjs | null;
  recordStatus?: string | null;
  uploadRemark?: string | null;
}

export const defaultValue: Readonly<IFimSettAcctWq> = {};

import dayjs from 'dayjs';

export interface IFimAccounts {
  id?: number;
  accountId?: string | null;
  custId?: string;
  relnId?: string;
  relnType?: string;
  operInst?: string | null;
  isAcctNbr?: string;
  bndAcctNbr?: string;
  closingId?: string | null;
  subSegment?: string | null;
  branchCode?: string | null;
  acctStatus?: string;
  ctryCode?: string | null;
  acctOwners?: string | null;
  remarks?: string | null;
  createdBy?: string | null;
  createdTs?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedTs?: dayjs.Dayjs | null;
  recordStatus?: string | null;
}

export const defaultValue: Readonly<IFimAccounts> = {};

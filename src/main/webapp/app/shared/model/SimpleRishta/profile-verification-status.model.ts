import dayjs from 'dayjs';

export interface IProfileVerificationStatus {
  id?: number;
  attributeName?: string | null;
  status?: string | null;
  verifiedAt?: dayjs.Dayjs | null;
  verifiedBy?: string | null;
}

export const defaultValue: Readonly<IProfileVerificationStatus> = {};

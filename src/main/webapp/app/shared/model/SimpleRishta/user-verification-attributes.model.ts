import dayjs from 'dayjs';

export interface IUserVerificationAttributes {
  id?: number;
  documentType?: string | null;
  documentUrl?: string | null;
  status?: string | null;
  verificationToken?: string | null;
  lastActionDate?: dayjs.Dayjs | null;
  createdAt?: dayjs.Dayjs;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IUserVerificationAttributes> = {};

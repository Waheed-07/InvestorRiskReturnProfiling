import dayjs from 'dayjs';

export interface IVerifiableAttributes {
  id?: number;
  name?: string;
  createdBy?: string;
  createdAt?: dayjs.Dayjs;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IVerifiableAttributes> = {};

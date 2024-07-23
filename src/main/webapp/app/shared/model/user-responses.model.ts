import dayjs from 'dayjs';

export interface IUserResponses {
  id?: number;
  responseText?: string;
  responseDate?: dayjs.Dayjs;
}

export const defaultValue: Readonly<IUserResponses> = {};

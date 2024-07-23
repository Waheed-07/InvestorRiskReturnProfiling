import dayjs from 'dayjs';

export interface IQuestions {
  id?: number;
  questionText?: string;
  questionType?: string;
  createdAt?: dayjs.Dayjs;
}

export const defaultValue: Readonly<IQuestions> = {};

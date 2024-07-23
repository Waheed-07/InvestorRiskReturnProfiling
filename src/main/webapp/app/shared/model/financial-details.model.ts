import dayjs from 'dayjs';

export interface IFinancialDetails {
  id?: number;
  annualIncome?: number;
  netWorth?: number;
  currentSavings?: number | null;
  investmentExperience?: string;
  sourceOfFunds?: string;
  createdAt?: dayjs.Dayjs;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IFinancialDetails> = {};

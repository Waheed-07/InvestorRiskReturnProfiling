export interface ICurrency {
  id?: number;
  name?: string | null;
  code?: string | null;
}

export const defaultValue: Readonly<ICurrency> = {};

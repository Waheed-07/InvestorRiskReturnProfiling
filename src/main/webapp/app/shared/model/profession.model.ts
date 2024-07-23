export interface IProfession {
  id?: number;
  name?: string | null;
  conceptUri?: string | null;
  iscoGroup?: string | null;
  description?: string | null;
}

export const defaultValue: Readonly<IProfession> = {};

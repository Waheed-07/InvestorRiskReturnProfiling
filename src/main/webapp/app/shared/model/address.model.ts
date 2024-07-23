export interface IAddress {
  id?: number;
  addressLineOne?: string | null;
  addressLineTwo?: string | null;
  postalCode?: string | null;
  city?: string | null;
  region?: string | null;
}

export const defaultValue: Readonly<IAddress> = {};

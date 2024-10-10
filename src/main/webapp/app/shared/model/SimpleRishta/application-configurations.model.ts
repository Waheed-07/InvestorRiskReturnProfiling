export interface IApplicationConfigurations {
  id?: number;
  configKey?: string;
  configValue?: string;
  description?: string | null;
  countryCode?: string | null;
}

export const defaultValue: Readonly<IApplicationConfigurations> = {};

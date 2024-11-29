import React, { useEffect, useState } from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';
import { addTranslationSourcePrefix } from 'app/shared/reducers/locale';
import { useAppDispatch, useAppSelector } from 'app/config/store';

const EntitiesMenu = () => {
  const lastChange = useAppSelector(state => state.locale.lastChange);
  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(addTranslationSourcePrefix('services/fim/'));
  }, [lastChange]);

  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/fim/fim-accounts">
        <Translate contentKey="global.menu.entities.fimFimAccounts" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/fim-accounts-wq">
        <Translate contentKey="global.menu.entities.fimFimAccountsWq" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/fim-accounts-history">
        <Translate contentKey="global.menu.entities.fimFimAccountsHistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/fim-sett-acct">
        <Translate contentKey="global.menu.entities.fimFimSettAcct" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/fim-sett-acct-wq">
        <Translate contentKey="global.menu.entities.fimFimSettAcctWq" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/fim-sett-acct-history">
        <Translate contentKey="global.menu.entities.fimFimSettAcctHistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/fim-cust">
        <Translate contentKey="global.menu.entities.fimFimCust" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/fim-cust-wq">
        <Translate contentKey="global.menu.entities.fimFimCustWq" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/fim-cust-history">
        <Translate contentKey="global.menu.entities.fimFimCustHistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/fim/ethnicity">
        <Translate contentKey="global.menu.entities.fimEthnicity" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;

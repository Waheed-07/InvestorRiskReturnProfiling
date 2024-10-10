import React, { useEffect } from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';
import { addTranslationSourcePrefix } from 'app/shared/reducers/locale';
import { useAppDispatch, useAppSelector } from 'app/config/store';

const EntitiesMenu = () => {
  const lastChange = useAppSelector(state => state.locale.lastChange);
  const dispatch = useAppDispatch();
  useEffect(() => {
    dispatch(addTranslationSourcePrefix('services/simplerishta/'));
  }, [lastChange]);

  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/simplerishta/verifiable-attributes">
        <Translate contentKey="global.menu.entities.simpleRishtaVerifiableAttributes" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/simplerishta/user-verification-attributes">
        <Translate contentKey="global.menu.entities.simpleRishtaUserVerificationAttributes" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/simplerishta/application-configurations">
        <Translate contentKey="global.menu.entities.simpleRishtaApplicationConfigurations" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/simplerishta/profile-verification-status">
        <Translate contentKey="global.menu.entities.simpleRishtaProfileVerificationStatus" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;

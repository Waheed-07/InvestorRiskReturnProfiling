import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/personal-details">
        <Translate contentKey="global.menu.entities.personalDetails" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/country">
        <Translate contentKey="global.menu.entities.country" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/financial-details">
        <Translate contentKey="global.menu.entities.financialDetails" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/profession">
        <Translate contentKey="global.menu.entities.profession" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/address">
        <Translate contentKey="global.menu.entities.address" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/currency">
        <Translate contentKey="global.menu.entities.currency" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/contact">
        <Translate contentKey="global.menu.entities.contact" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/questions">
        <Translate contentKey="global.menu.entities.questions" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/user-responses">
        <Translate contentKey="global.menu.entities.userResponses" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/options">
        <Translate contentKey="global.menu.entities.options" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;

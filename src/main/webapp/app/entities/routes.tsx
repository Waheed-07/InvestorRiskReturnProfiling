import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import { ReducersMapObject, combineReducers } from '@reduxjs/toolkit';

import getStore from 'app/config/store';

import entitiesReducers from './reducers';

import VerifiableAttributes from './SimpleRishta/verifiable-attributes';
import UserVerificationAttributes from './SimpleRishta/user-verification-attributes';
import ApplicationConfigurations from './SimpleRishta/application-configurations';
import ProfileVerificationStatus from './SimpleRishta/profile-verification-status';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  const store = getStore();
  store.injectReducer('simplerishta', combineReducers(entitiesReducers as ReducersMapObject));
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="/verifiable-attributes/*" element={<VerifiableAttributes />} />
        <Route path="/user-verification-attributes/*" element={<UserVerificationAttributes />} />
        <Route path="/application-configurations/*" element={<ApplicationConfigurations />} />
        <Route path="/profile-verification-status/*" element={<ProfileVerificationStatus />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};

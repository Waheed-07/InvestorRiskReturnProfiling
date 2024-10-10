import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ApplicationConfigurations from './application-configurations';
import ApplicationConfigurationsDetail from './application-configurations-detail';
import ApplicationConfigurationsUpdate from './application-configurations-update';
import ApplicationConfigurationsDeleteDialog from './application-configurations-delete-dialog';

const ApplicationConfigurationsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ApplicationConfigurations />} />
    <Route path="new" element={<ApplicationConfigurationsUpdate />} />
    <Route path=":id">
      <Route index element={<ApplicationConfigurationsDetail />} />
      <Route path="edit" element={<ApplicationConfigurationsUpdate />} />
      <Route path="delete" element={<ApplicationConfigurationsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ApplicationConfigurationsRoutes;

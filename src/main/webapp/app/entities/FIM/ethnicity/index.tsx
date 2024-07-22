import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Ethnicity from './ethnicity';
import EthnicityDetail from './ethnicity-detail';
import EthnicityUpdate from './ethnicity-update';
import EthnicityDeleteDialog from './ethnicity-delete-dialog';

const EthnicityRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Ethnicity />} />
    <Route path="new" element={<EthnicityUpdate />} />
    <Route path=":id">
      <Route index element={<EthnicityDetail />} />
      <Route path="edit" element={<EthnicityUpdate />} />
      <Route path="delete" element={<EthnicityDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default EthnicityRoutes;

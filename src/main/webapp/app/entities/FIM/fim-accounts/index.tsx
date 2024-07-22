import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimAccounts from './fim-accounts';
import FimAccountsDetail from './fim-accounts-detail';
import FimAccountsUpdate from './fim-accounts-update';
import FimAccountsDeleteDialog from './fim-accounts-delete-dialog';

const FimAccountsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimAccounts />} />
    <Route path="new" element={<FimAccountsUpdate />} />
    <Route path=":id">
      <Route index element={<FimAccountsDetail />} />
      <Route path="edit" element={<FimAccountsUpdate />} />
      <Route path="delete" element={<FimAccountsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimAccountsRoutes;

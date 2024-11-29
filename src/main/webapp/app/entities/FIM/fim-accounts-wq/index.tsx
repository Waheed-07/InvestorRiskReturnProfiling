import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimAccountsWq from './fim-accounts-wq';
import FimAccountsWqDetail from './fim-accounts-wq-detail';
import FimAccountsWqUpdate from './fim-accounts-wq-update';
import FimAccountsWqDeleteDialog from './fim-accounts-wq-delete-dialog';

const FimAccountsWqRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimAccountsWq />} />
    <Route path="new" element={<FimAccountsWqUpdate />} />
    <Route path=":id">
      <Route index element={<FimAccountsWqDetail />} />
      <Route path="edit" element={<FimAccountsWqUpdate />} />
      <Route path="delete" element={<FimAccountsWqDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimAccountsWqRoutes;

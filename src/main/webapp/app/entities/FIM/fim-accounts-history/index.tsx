import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimAccountsHistory from './fim-accounts-history';
import FimAccountsHistoryDetail from './fim-accounts-history-detail';
import FimAccountsHistoryUpdate from './fim-accounts-history-update';
import FimAccountsHistoryDeleteDialog from './fim-accounts-history-delete-dialog';

const FimAccountsHistoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimAccountsHistory />} />
    <Route path="new" element={<FimAccountsHistoryUpdate />} />
    <Route path=":id">
      <Route index element={<FimAccountsHistoryDetail />} />
      <Route path="edit" element={<FimAccountsHistoryUpdate />} />
      <Route path="delete" element={<FimAccountsHistoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimAccountsHistoryRoutes;

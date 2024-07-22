import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimSettAcctHistory from './fim-sett-acct-history';
import FimSettAcctHistoryDetail from './fim-sett-acct-history-detail';
import FimSettAcctHistoryUpdate from './fim-sett-acct-history-update';
import FimSettAcctHistoryDeleteDialog from './fim-sett-acct-history-delete-dialog';

const FimSettAcctHistoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimSettAcctHistory />} />
    <Route path="new" element={<FimSettAcctHistoryUpdate />} />
    <Route path=":id">
      <Route index element={<FimSettAcctHistoryDetail />} />
      <Route path="edit" element={<FimSettAcctHistoryUpdate />} />
      <Route path="delete" element={<FimSettAcctHistoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimSettAcctHistoryRoutes;

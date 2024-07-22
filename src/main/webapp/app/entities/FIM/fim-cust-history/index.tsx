import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimCustHistory from './fim-cust-history';
import FimCustHistoryDetail from './fim-cust-history-detail';
import FimCustHistoryUpdate from './fim-cust-history-update';
import FimCustHistoryDeleteDialog from './fim-cust-history-delete-dialog';

const FimCustHistoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimCustHistory />} />
    <Route path="new" element={<FimCustHistoryUpdate />} />
    <Route path=":id">
      <Route index element={<FimCustHistoryDetail />} />
      <Route path="edit" element={<FimCustHistoryUpdate />} />
      <Route path="delete" element={<FimCustHistoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimCustHistoryRoutes;

import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimCust from './fim-cust';
import FimCustDetail from './fim-cust-detail';
import FimCustUpdate from './fim-cust-update';
import FimCustDeleteDialog from './fim-cust-delete-dialog';

const FimCustRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimCust />} />
    <Route path="new" element={<FimCustUpdate />} />
    <Route path=":id">
      <Route index element={<FimCustDetail />} />
      <Route path="edit" element={<FimCustUpdate />} />
      <Route path="delete" element={<FimCustDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimCustRoutes;

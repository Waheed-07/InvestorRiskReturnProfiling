import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimCustWq from './fim-cust-wq';
import FimCustWqDetail from './fim-cust-wq-detail';
import FimCustWqUpdate from './fim-cust-wq-update';
import FimCustWqDeleteDialog from './fim-cust-wq-delete-dialog';

const FimCustWqRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimCustWq />} />
    <Route path="new" element={<FimCustWqUpdate />} />
    <Route path=":id">
      <Route index element={<FimCustWqDetail />} />
      <Route path="edit" element={<FimCustWqUpdate />} />
      <Route path="delete" element={<FimCustWqDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimCustWqRoutes;

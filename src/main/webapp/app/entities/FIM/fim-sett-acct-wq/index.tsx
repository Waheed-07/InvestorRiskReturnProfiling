import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import FimSettAcctWq from './fim-sett-acct-wq';
import FimSettAcctWqDetail from './fim-sett-acct-wq-detail';
import FimSettAcctWqUpdate from './fim-sett-acct-wq-update';
import FimSettAcctWqDeleteDialog from './fim-sett-acct-wq-delete-dialog';

const FimSettAcctWqRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<FimSettAcctWq />} />
    <Route path="new" element={<FimSettAcctWqUpdate />} />
    <Route path=":id">
      <Route index element={<FimSettAcctWqDetail />} />
      <Route path="edit" element={<FimSettAcctWqUpdate />} />
      <Route path="delete" element={<FimSettAcctWqDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default FimSettAcctWqRoutes;

import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import UserResponses from './user-responses';
import UserResponsesDetail from './user-responses-detail';
import UserResponsesUpdate from './user-responses-update';
import UserResponsesDeleteDialog from './user-responses-delete-dialog';

const UserResponsesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<UserResponses />} />
    <Route path="new" element={<UserResponsesUpdate />} />
    <Route path=":id">
      <Route index element={<UserResponsesDetail />} />
      <Route path="edit" element={<UserResponsesUpdate />} />
      <Route path="delete" element={<UserResponsesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default UserResponsesRoutes;

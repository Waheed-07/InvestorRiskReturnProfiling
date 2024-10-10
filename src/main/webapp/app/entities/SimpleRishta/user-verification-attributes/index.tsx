import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import UserVerificationAttributes from './user-verification-attributes';
import UserVerificationAttributesDetail from './user-verification-attributes-detail';
import UserVerificationAttributesUpdate from './user-verification-attributes-update';
import UserVerificationAttributesDeleteDialog from './user-verification-attributes-delete-dialog';

const UserVerificationAttributesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<UserVerificationAttributes />} />
    <Route path="new" element={<UserVerificationAttributesUpdate />} />
    <Route path=":id">
      <Route index element={<UserVerificationAttributesDetail />} />
      <Route path="edit" element={<UserVerificationAttributesUpdate />} />
      <Route path="delete" element={<UserVerificationAttributesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default UserVerificationAttributesRoutes;

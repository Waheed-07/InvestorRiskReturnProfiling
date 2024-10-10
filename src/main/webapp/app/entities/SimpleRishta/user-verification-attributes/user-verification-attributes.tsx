import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { TextFormat, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './user-verification-attributes.reducer';

export const UserVerificationAttributes = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const userVerificationAttributesList = useAppSelector(state => state.simplerishta.userVerificationAttributes.entities);
  const loading = useAppSelector(state => state.simplerishta.userVerificationAttributes.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="user-verification-attributes-heading" data-cy="UserVerificationAttributesHeading">
        <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.home.title">User Verification Attributes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/simplerishta/user-verification-attributes/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.home.createLabel">
              Create new User Verification Attributes
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {userVerificationAttributesList && userVerificationAttributesList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('documentType')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.documentType">Document Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('documentType')} />
                </th>
                <th className="hand" onClick={sort('documentUrl')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.documentUrl">Document Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('documentUrl')} />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('status')} />
                </th>
                <th className="hand" onClick={sort('verificationToken')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.verificationToken">
                    Verification Token
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('verificationToken')} />
                </th>
                <th className="hand" onClick={sort('lastActionDate')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.lastActionDate">Last Action Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lastActionDate')} />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.createdAt">Created At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.updatedAt">Updated At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {userVerificationAttributesList.map((userVerificationAttributes, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button
                      tag={Link}
                      to={`/simplerishta/user-verification-attributes/${userVerificationAttributes.id}`}
                      color="link"
                      size="sm"
                    >
                      {userVerificationAttributes.id}
                    </Button>
                  </td>
                  <td>{userVerificationAttributes.documentType}</td>
                  <td>{userVerificationAttributes.documentUrl}</td>
                  <td>{userVerificationAttributes.status}</td>
                  <td>{userVerificationAttributes.verificationToken}</td>
                  <td>
                    {userVerificationAttributes.lastActionDate ? (
                      <TextFormat type="date" value={userVerificationAttributes.lastActionDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {userVerificationAttributes.createdAt ? (
                      <TextFormat type="date" value={userVerificationAttributes.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {userVerificationAttributes.updatedAt ? (
                      <TextFormat type="date" value={userVerificationAttributes.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/simplerishta/user-verification-attributes/${userVerificationAttributes.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/simplerishta/user-verification-attributes/${userVerificationAttributes.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/simplerishta/user-verification-attributes/${userVerificationAttributes.id}/delete`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="simpleRishtaApp.simpleRishtaUserVerificationAttributes.home.notFound">
                No User Verification Attributes found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default UserVerificationAttributes;

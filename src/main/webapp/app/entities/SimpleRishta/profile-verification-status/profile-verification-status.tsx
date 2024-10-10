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

import { getEntities } from './profile-verification-status.reducer';

export const ProfileVerificationStatus = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const profileVerificationStatusList = useAppSelector(state => state.simplerishta.profileVerificationStatus.entities);
  const loading = useAppSelector(state => state.simplerishta.profileVerificationStatus.loading);

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
      <h2 id="profile-verification-status-heading" data-cy="ProfileVerificationStatusHeading">
        <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.home.title">Profile Verification Statuses</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/simplerishta/profile-verification-status/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.home.createLabel">
              Create new Profile Verification Status
            </Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {profileVerificationStatusList && profileVerificationStatusList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('attributeName')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.attributeName">Attribute Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('attributeName')} />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('status')} />
                </th>
                <th className="hand" onClick={sort('verifiedAt')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.verifiedAt">Verified At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('verifiedAt')} />
                </th>
                <th className="hand" onClick={sort('verifiedBy')}>
                  <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.verifiedBy">Verified By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('verifiedBy')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {profileVerificationStatusList.map((profileVerificationStatus, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button
                      tag={Link}
                      to={`/simplerishta/profile-verification-status/${profileVerificationStatus.id}`}
                      color="link"
                      size="sm"
                    >
                      {profileVerificationStatus.id}
                    </Button>
                  </td>
                  <td>{profileVerificationStatus.attributeName}</td>
                  <td>{profileVerificationStatus.status}</td>
                  <td>
                    {profileVerificationStatus.verifiedAt ? (
                      <TextFormat type="date" value={profileVerificationStatus.verifiedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{profileVerificationStatus.verifiedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/simplerishta/profile-verification-status/${profileVerificationStatus.id}`}
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
                        to={`/simplerishta/profile-verification-status/${profileVerificationStatus.id}/edit`}
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
                          (window.location.href = `/simplerishta/profile-verification-status/${profileVerificationStatus.id}/delete`)
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
              <Translate contentKey="simpleRishtaApp.simpleRishtaProfileVerificationStatus.home.notFound">
                No Profile Verification Statuses found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ProfileVerificationStatus;

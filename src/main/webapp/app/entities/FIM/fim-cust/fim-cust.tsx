import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './fim-cust.reducer';

export const FimCust = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const fimCustList = useAppSelector(state => state.fim.fimCust.entities);
  const loading = useAppSelector(state => state.fim.fimCust.loading);
  const totalItems = useAppSelector(state => state.fim.fimCust.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="fim-cust-heading" data-cy="FimCustHeading">
        <Translate contentKey="fimApp.fimFimCust.home.title">Fim Custs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="fimApp.fimFimCust.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/fim/fim-cust/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="fimApp.fimFimCust.home.createLabel">Create new Fim Cust</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {fimCustList && fimCustList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="fimApp.fimFimCust.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('custId')}>
                  <Translate contentKey="fimApp.fimFimCust.custId">Cust Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('custId')} />
                </th>
                <th className="hand" onClick={sort('clientId')}>
                  <Translate contentKey="fimApp.fimFimCust.clientId">Client Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('clientId')} />
                </th>
                <th className="hand" onClick={sort('idType')}>
                  <Translate contentKey="fimApp.fimFimCust.idType">Id Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('idType')} />
                </th>
                <th className="hand" onClick={sort('ctryCode')}>
                  <Translate contentKey="fimApp.fimFimCust.ctryCode">Ctry Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ctryCode')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="fimApp.fimFimCust.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdTs')}>
                  <Translate contentKey="fimApp.fimFimCust.createdTs">Created Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdTs')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="fimApp.fimFimCust.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedTs')}>
                  <Translate contentKey="fimApp.fimFimCust.updatedTs">Updated Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedTs')} />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  <Translate contentKey="fimApp.fimFimCust.recordStatus">Record Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('recordStatus')} />
                </th>
                <th className="hand" onClick={sort('uploadRemark')}>
                  <Translate contentKey="fimApp.fimFimCust.uploadRemark">Upload Remark</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uploadRemark')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fimCustList.map((fimCust, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/fim/fim-cust/${fimCust.id}`} color="link" size="sm">
                      {fimCust.id}
                    </Button>
                  </td>
                  <td>{fimCust.custId}</td>
                  <td>{fimCust.clientId}</td>
                  <td>{fimCust.idType}</td>
                  <td>{fimCust.ctryCode}</td>
                  <td>{fimCust.createdBy}</td>
                  <td>{fimCust.createdTs ? <TextFormat type="date" value={fimCust.createdTs} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{fimCust.updatedBy}</td>
                  <td>{fimCust.updatedTs ? <TextFormat type="date" value={fimCust.updatedTs} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{fimCust.recordStatus}</td>
                  <td>{fimCust.uploadRemark}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/fim/fim-cust/${fimCust.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/fim/fim-cust/${fimCust.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                          (window.location.href = `/fim/fim-cust/${fimCust.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
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
              <Translate contentKey="fimApp.fimFimCust.home.notFound">No Fim Custs found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={fimCustList && fimCustList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default FimCust;
